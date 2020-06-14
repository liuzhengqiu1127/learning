from functools import reduce


def main():
    list = [1, 2, 3, 4, 5]
    print(list[:-1])
    print(list[-1])
    for item in list:
        print(item)

    for index, item in enumerate(list):
         print('index={},value={}'.format(index, item))

    d = {'name':'jason','dob':'2020-06-13','gender':'male'}
    for k in d:
        print(k)

    for v in d.values():
        print(v)

    for k, v in d.items():
        print('key: {}, value: {}'.format(k, v))

def test() :
    text = ' Today,  is, Sunday'
    text_list = [s.strip() for s in text.split(',') if len(s.strip()) > 5]
    print(text_list)

def test01():
    attributes = ['name', 'dob', 'gender']
    values = [['jason', '2000-01-01', 'male'], ['mike', '1999-01-01', 'male'], ['nancy', '2001-02-01', 'female']]
    result = [dict(zip(attributes, value)) for value in values]
    print(result)

# MIN_VALUE = 1
def my_sum(a, b):
    # global MIN_VALUE
    # MIN_VALUE += 1
    return a + b

def nth_power(exponent):
    def exponent_of(base):
        return base ** exponent
    return exponent_of

def test02():
    l = [1, 2, 3, 4, 5]
    new_list1 = map(lambda x: x * 2, l)
    for value in new_list1:
        print(value)
    # print(new_list1)
    new_list2 = filter(lambda x: x % 2 == 0, l)
    print(new_list2)
    product = reduce(lambda x, y: x*y, l)
    print(product)

if __name__ == '__main__':
    d = {'mike': 10, 'lucy': 2, 'ben': 30}
    v = sorted(d.items(), key=lambda x: x[1], reverse=True)
    print(v)