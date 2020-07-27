import numpy as np


def test001():
    x1 = np.arange(1, 11, 2)
    x2 = np.linspace(1, 9, 5)
    print(np.add(x1, x2))
    print(np.subtract(x1, x2))
    print(np.multiply(x1, x2))
    print(np.divide(x1, x2))
    print(np.power(x1, x2))
    print(np.remainder(x1, x2))


def test002():
    a = np.array([
        [[1, 2, 3], [4, 5, 6], [7, 8, 9]],
        [[10, 11, 12], [13, 14, 15], [16, 17, 18]]
        ])
    print(np.amax(a))    # 求所有
    print(np.amax(a, 0))  #  求二维
    print(np.amax(a, 1))  # 求一维
    print(np.amax(a, 2))  # 求 每个数组


def test003():
    a = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    print(np.ptp(a))
    print(np.ptp(a, 0))
    print(np.ptp(a, 1))


if __name__ == '__main__':
    test003()