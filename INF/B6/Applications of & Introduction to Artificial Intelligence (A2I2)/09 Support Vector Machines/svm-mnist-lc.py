# uncomment if used as Azure-Notebook (update of scikit-learn)
#!pip install scikit-learn -U
import numpy as np
import sklearn

from sklearn import svm, metrics
from sklearn.datasets import fetch_openml
from sklearn.model_selection import train_test_split

from scipy import fftpack

def compute_dct_features_2d(image, no_coeff=10):
    """
    compute 2D-DCT features of a given image (Type-II DCT).
    :param image: 2D input image in 'C' format
    :param no_coeff: number of desired DCT coefficients (zig-zag-scan)
    :return: DCT features
    """
    # 1D-DCT along columns
    dctImg = fftpack.dct(image,type=2,axis=1,norm='ortho')
    # 1D-DCT along (already transformed) rows
    dctImg = fftpack.dct(dctImg,type=2,axis=0,norm='ortho')

    # zig-zag-scan in resultuing transformed image
    features = np.concatenate([np.diagonal(dctImg[::-1, :], k)[::(2 * (k % 2) - 1)] for k in range(1 - dctImg.shape[0], dctImg.shape[0])])
    # extract first no_coeff coefficients
    features = features[0:no_coeff]

    return features


print('The scikit-learn version is {}.'.format(sklearn.__version__))

# set to desired amount of training samples for faster training (max. 60000 available)
noTrainSamples = 2000

# set True for DCT features
useDCT = False
# number of DCT coefficients to use as features
noDCTCoeffs = 50

print('Loading MNIST...')
X, y = fetch_openml('mnist_784', version=1, return_X_y=True)
y = y.astype(np.uint8)
print('...finished')
print('Size of MNIST data: ', X.shape)

if useDCT == True:
    print('Computing DCT, using first', noDCTCoeffs, ' coefficients...')
    print('Not implemented yet!')
    exit(0)
    # todo:
    # reshape each input image to 2D (size 28x28)
    # compute first "noDCTCoeffs" DCT coefficients
    # store result in list
    # at the end, X should contain one vector of DCT-coefficients per image (shape: 70000, noDCTCoeffs)
else:
    X = X.reshape((X.shape[0], -1))

print('Size of data set: ', X.shape)

print('Starting Training...')
X_train, X_test, y_train, y_test = train_test_split(
    X, y, train_size=noTrainSamples, test_size=10000)

classifier = svm.SVC(kernel='linear', C=1.0, gamma='scale')
classifier.fit(X_train, y_train)
print('...finished')


print('Evaluation on test set...')
predicted = classifier.predict(X_test)

print("Classification report for classifier %s:\n%s\n"
      % (classifier, metrics.classification_report(y_test, predicted)))
disp = metrics.plot_confusion_matrix(classifier, X_test, y_test)
disp.figure_.suptitle("Confusion Matrix")
print("Confusion matrix:\n%s" % disp.confusion_matrix)
