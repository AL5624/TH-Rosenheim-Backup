import numpy as np
import matplotlib.pyplot as plt

x_lim_low = -10
x_lim_high = 10
y_lim_low = -10
y_lim_high = 10

def generate_samples(count, x_mean, x_std, y_mean, y_std):
    x = np.random.normal(x_mean, x_std, count)
    y = np.random.normal(y_mean, y_std, count)

    return x, y


def generate_dataset(count, x1_mean, x1_std, y1_mean, y1_std, x2_mean, x2_std, y2_mean, y2_std, ratio):
    n_N = count // (ratio + 1)
    n_P = count - n_N

    samples_P = np.empty((n_P, 2))
    samples_P[:, 0], samples_P[:, 1] = generate_samples(n_P, x1_mean, x1_std, y1_mean, y1_std)

    samples_N = np.empty((n_N, 2))
    samples_N[:, 0], samples_N[:, 1] = generate_samples(n_N, x2_mean, x2_std, y2_mean, y2_std)

    samples = np.append(samples_P, samples_N, axis=0)
    labels = np.full((n_P), 1)
    labels = np.append(labels, np.full((n_N), 0))
    return samples, labels


def my_plot(scale, **kwargs):

    plt.style.use('classic')
    fig, ax = plt.subplots()

    if scale == True:
        plt.xlim(x_lim_low, x_lim_high)
        plt.ylim(y_lim_low, y_lim_high)

    X1 = kwargs.get('X1', None)
    X2 = kwargs.get('X2', None)
    y1 = kwargs.get('y1', None)
    y2 = kwargs.get('y2', None)

    X1_n = np.empty((int(len(y1) - np.sum(y1)), 2))
    X1_p = np.empty((int(np.sum(y1)), 2))

    c1 = 0
    c2 = 0
    for i in range(0, len(y1)):
        if y1[i] == 0:
            X1_n[c1] = X1[i]
            c1 += 1
        else:
            X1_p[c2] = X1[i]
            c2 += 1

    ax.plot(X1_n[:, 0], X1_n[:, 1], 'r+', label='N')
    ax.plot(X1_p[:, 0], X1_p[:, 1], 'g+', label='P')

    try:
        X2_n = np.empty((int(len(y2) - np.sum(y2)), 2))
        X2_p = np.empty((int(np.sum(y2)), 2))
        c1 = 0
        c2 = 0
        for i in range(0, len(y2)):
            if y2[i] == 0:
                X2_n[c1] = X2[i]
                c1 += 1
            else:
                X2_p[c2] = X2[i]
                c2 += 1

        ax.plot(X2_n[:, 0], X2_n[:, 1], 'y+', label='N X2')
        ax.plot(X2_p[:, 0], X2_p[:, 1], 'b+', label='P X2')
    except:
        pass
    ax.legend(loc='lower right')
    leg = ax.legend()
    plt.show()
    fig


def error_plot(scale, **kwargs):
    x_lim_low = -10
    x_lim_high = 10
    y_lim_low = -10
    y_lim_high = 10

    plt.style.use('classic')
    fig, ax = plt.subplots()

    if scale == True:
        plt.xlim(x_lim_low, x_lim_high)
        plt.ylim(y_lim_low, y_lim_high)

    X1 = kwargs.get('X1', None)
    X2 = kwargs.get('X2', None)
    y1 = kwargs.get('y1', None)
    y2 = kwargs.get('y2', None)

    X1_n = np.empty((int(len(y1) - np.sum(y1)), 2))
    X1_p = np.empty((int(np.sum(y1)), 2))

    c1 = 0
    c2 = 0
    for i in range(0, len(y1)):
        if y1[i] == 0:
            X1_n[c1] = X1[i]
            c1 += 1
        else:
            X1_p[c2] = X1[i]
            c2 += 1

    ax.plot(X1_n[:, 0], X1_n[:, 1], 'r+', label='N')
    ax.plot(X1_p[:, 0], X1_p[:, 1], 'g+', label='P')

    try:
        X2_n = np.empty((int(len(y2) - np.sum(y2)), 2))
        X2_p = np.empty((int(np.sum(y2)), 2))
        c1 = 0
        c2 = 0
        for i in range(0, len(y2)):
            if y2[i] == 0:
                X2_n[c1] = X2[i]
                c1 += 1
            else:
                X2_p[c2] = X2[i]
                c2 += 1

        ax.plot(X2_n[:, 0], X2_n[:, 1], 'yo', label='FN')
        ax.plot(X2_p[:, 0], X2_p[:, 1], 'bo', label='FP')
    except:
        pass
    ax.legend(loc='lower right')
    leg = ax.legend()
    fig


def detect_misclassifications(X_test, y_test, pred):
    cor = []
    for p in range(0, len(pred)):
        if pred[p] == y_test[p]:
            cor = np.append(cor, int(p))

    cor = cor.astype(int)

    y_test = np.delete(y_test, cor)
    pred = np.delete(pred, cor)
    X_test = np.delete(X_test, cor, 0)
    return X_test, pred
