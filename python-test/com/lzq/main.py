
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

if __name__ == '__main__':
    square = nth_power(2)
    cube = nth_power(3)
    print(square(2))
    print(cube(2))