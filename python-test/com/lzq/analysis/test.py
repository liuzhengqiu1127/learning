from filecmp import cmp
from functools import cmp_to_key

import numpy as np

if __name__ == '__main__':
    person_type = np.dtype({
        'names': ['name', 'age', 'chinese', 'english', 'math'],
        'formats': ['S32', 'i', 'i', 'i', 'f']
    })

    peoples = np.array([("ZF", 32, 66, 65, 30),
                        ("GY", 24, 95, 85, 98),
                        ("ZY", 28, 93, 92, 96),
                        ("HZ", 29, 90, 88, 77),
                        ("WD", 29, 80, 90, 90)
                        ], dtype=person_type)

    ages = peoples[:]['age']
    chineses = peoples[:]['chinese']
    maths = peoples[:]['math']
    englishs = peoples[:]['english']

    print("语文平均成绩={},最小成绩={}，最大成绩={}，方差={}，标准差={}".format(
        np.mean(chineses), np.min(chineses), np.max(chineses), np.var(chineses), np.std(chineses)))

    ranking = sorted(peoples, key=cmp_to_key(lambda x, y: int(x[2]+x[3]+x[4])-int(y[2]+y[3]+y[4])), reverse=True)
    print(ranking)


