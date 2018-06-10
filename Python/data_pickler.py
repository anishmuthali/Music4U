import numpy as np
import _pickle as pickle
import csv

csvfile = open("fer2013.csv",mode="r")
csvread = csv.reader(csvfile,delimiter=',')
images = []
outputs = []
i = 0
for row in csvread:
    if i%100 == 0:
        print(i)
    if i > 0 and i<35887:
        currpixels = np.asarray(list(map(int,row[1].split())))
        currpixels = np.reshape(currpixels,(48,48,1))
        curroh = np.zeros((7,1))
        curroh[int(row[0])] = 1
        images.append(currpixels)
        outputs.append(curroh)
    i+=1

print("pickling")
images = np.asarray(images)
outputs = np.asarray(outputs)
img_file = open("images.pickle","wb")
pickle.dump(images,img_file)
img_file.close()
out_file = open("outputs.pickle","wb")
pickle.dump(outputs,out_file)
out_file.close()
    
