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

model = load_model('model.h5')
im = np.asarray(Image.open('test.jpg'))
im = np.reshape(im,(1,48,48,1))
pred = model.predict(im)
maxind = np.argmax(pred)
emotlist = ["angry","disgust","fear","happy","sad","surprise","neutral"]
print(emotlist[maxind])
x = urllib.request.urlopen('http://localhost:4567/' + str(emotlist[maxind]))
print(x.read())

    

