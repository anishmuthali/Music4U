from flask import Flask, request, render_template, g
from flask_uploads import UploadSet, configure_uploads, IMAGES
from keras.models import load_model
import numpy as np
import _pickle as pickle
import csv
from keras.layers import Conv2D, Dense, Flatten, MaxPooling2D, Dropout
from keras.layers.normalization import BatchNormalization
from keras import regularizers
from keras.models import Sequential
from keras.optimizers import Adam
from keras.callbacks import ModelCheckpoint
from PIL import Image
import urllib.request
import cv2
import sys
import os
import tensorflow as tf
from keras import backend as K

app = Flask(__name__)
photos = UploadSet('photos', IMAGES)

app.config['UPLOADED_PHOTOS_DEST'] = 'C:\\Users\\anish\\Desktop\\NeuralNetworks\\HarkerHacks'
configure_uploads(app, photos)
@app.before_request
def before_request():
    g.model = load_model('model.h5')
@app.route('/')
def home():
    return "Hey there!"

@app.route('/', methods=['POST'])
def result():
    K.clear_session()
    img_post = request.files['img']
    filename = photos.save(img_post)
    try:
        os.remove("test.jpg")
    except OSError:
        pass
    img_path = filename
    casc_path = "haarcascade_frontalface_default.xml"

    faceCascade = cv2.CascadeClassifier(casc_path)
    image = cv2.imread(img_path)
    gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
    faces = faceCascade.detectMultiScale(
        gray,
        scaleFactor=1.1,
        minNeighbors=5,
        minSize=(48, 48),
        flags = cv2.CASCADE_SCALE_IMAGE
    )
    print("Faces: " + str(len(faces)))
    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)
        crop_img = image[y:y+h, x:x+w]

    new_img = cv2.resize(crop_img, (48, 48))
    new_img = cv2.cvtColor(new_img, cv2.COLOR_BGR2GRAY)
    cv2.imwrite('test.jpg',new_img)

    model = load_model('model.h5')
    print("model loaded")
    im = np.asarray(Image.open('test.jpg'))
    im = np.reshape(im,(1,48,48,1))
    pred = model.predict(im)
    maxind = np.argmax(pred)
    emotlist = ["angry","disgust","fear","happy","sad","surprise","neutral"]
    x = urllib.request.urlopen('http://localhost:4567/' + str(emotlist[maxind]))
    os.remove(filename)
    model = None
    K.clear_session()
    return emotlist[maxind]

if __name__ == "__main__":
    app.run(debug=True)
    
