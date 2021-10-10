# for displaying images in Jupyter-Notebook uncomment the following line
# %matplotlib inline
import skimage.util as util
import skimage.filters as filters
import skimage.morphology as morpho

from skimage import data, io
from skimage.color import rgb2gray


original = data.chelsea()
grayscale = rgb2gray(original)

io.imshow(original)
io.show()
io.imshow(grayscale)
io.show()

stddev = 0.7
imgGauss = filters.gaussian(grayscale, stddev)
io.imshow(imgGauss)
io.show()

imgMean = filters.rank.mean(util.img_as_ubyte(grayscale), morpho.square(15))
io.imshow(imgMean)
io.show()
