import numpy as np
import _pickle as pickle
import csv
from keras.layers import Conv2D, Dense, Flatten, MaxPooling2D, Dropout
from keras.layers.normalization import BatchNormalization
from keras import regularizers
from keras.models import Sequential
from keras.optimizers import Adam
from keras.callbacks import ModelCheckpoint

img_file = open("images.pickle",mode="rb")
images = pickle.load(img_file)
img_file.close()
out_file = open("outputs.pickle",mode="rb")
outputs = pickle.load(out_file)
outputs = np.reshape(outputs,(outputs.shape[0],outputs.shape[1]))
out_file.close()
print("data loaded")

model = Sequential()
model.add(Conv2D(64,(3,3),strides=(1,1),padding="valid",activation="relu",input_shape=(48,48,1)))
model.add(BatchNormalization())
model.add(Dropout(0.45))
model.add(MaxPooling2D((2,2)))
model.add(Conv2D(128,(3,3),strides=(1,1),padding="valid",activation="relu"))
model.add(BatchNormalization())
model.add(Dropout(0.45))
model.add(MaxPooling2D((2,2)))
model.add(Conv2D(256,(3,3),strides=(1,1),padding="valid",activation="relu"))
model.add(BatchNormalization())
model.add(Dropout(0.45))
model.add(Flatten())
model.add(Dense(256,activation="relu"))
model.add(BatchNormalization())
model.add(Dropout(0.45))
model.add(Dense(512,activation="relu"))
model.add(BatchNormalization())
model.add(Dropout(0.45))
model.add(Dense(7,activation="softmax"))

epochs=20
optimizer = Adam(lr=0.001,beta_1=0.9,beta_2=0.999)
model.compile(optimizer="adam",loss='categorical_crossentropy',metrics=['accuracy'])
filepath="weights-improvement-{epoch:02d}-{val_acc:.2f}.h5"
checkpoint = ModelCheckpoint(filepath, monitor='val_acc', verbose=1, save_best_only=True, mode='max')
callbacks_list = [checkpoint]
history = model.fit(x=images,y=outputs,batch_size=128,epochs=epochs,callbacks=callbacks_list,verbose=2,validation_split=0.2)
          
