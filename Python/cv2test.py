import cv2

img_path = "testimg.jpg"
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
print("Found " + str(len(faces)) + " faces")
for (x, y, w, h) in faces:
    cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)
    crop_img = image[y:y+h, x:x+w]

new_img = cv2.resize(crop_img, (48, 48))
new_img = cv2.cvtColor(new_img, cv2.COLOR_BGR2GRAY)
cv2.imwrite('test.jpg',new_img)
