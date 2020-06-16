import re

def parse(text):
    #使用正则表达式去除标点符号和换行符
    text = re.sub(r'[^\w ]', ' ', text)
    text = text.lower()
    word_list = text.split(' ')
    word_list = filter(None, word_list)
    word_cnt = {}
    for word in word_list:
        if word not in word_cnt:
            word_cnt[word] = 0
        word_cnt[word] += 1

    sorted_word_cnt = sorted(word_cnt.items(), key=lambda kv: kv[1], reverse=True)
    return sorted_word_cnt

with open('in.txt', 'r') as fin:
    text = fin.read()

word_and_freq = parse(text)

with open('out.txt', 'w') as fout:
    for word, freq in word_and_freq:
        fout.write('{} {}\n'.format(word, freq))

def parse_to_word_list(text, last_word, word_list):
    text = re.sub(r'[^\w ]', ' ', last_word + text)
    text = text.lower()
    cur_word_list = text.split(' ')
    cur_word_list, last_word = cur_word_list[:-1], cur_word_list[-1]
    word_list += filter(None, cur_word_list)
    return last_word

def solve():
    with open('in.txt', 'r') as fin:
        word_list, last_word = [], ''
        while True:
            text = fin.read(20)
            if not text:
                break
            last_word = parse_to_word_list(text, last_word, word_list)

        word_cnt = {}
        for word in word_list:
            if word not in word_cnt:
                word_cnt[word] = 0
            word_cnt[word] += 1

        sorted_word_cnt = sorted(word_cnt.items(), key=lambda kv:kv[1], reverse=True)
        return sorted_word_cnt

if __name__ == '__main__':
    print(solve())
