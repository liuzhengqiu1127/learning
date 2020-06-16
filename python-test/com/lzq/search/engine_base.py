import os


class SearchEngineBase(object):
    def __init__(self):
        pass

    def add_corpus(self, file_path):
        with open(file_path, 'r') as fin:
            text = fin.read()
        self.process_corpus(file_path, text)

    def process_corpus(self, id, text):
        raise Exception('process_corpus not implemented.')

    def search(self, query):
        raise Exception('search not implemented.')

def main(search_engine):
    current_path = os.path.abspath(__file__)
    father_path = os.path.abspath(os.path.dirname(current_path) + os.path.sep + "..")
    config_file_path = father_path + os.path.sep + "file" + os.path.sep
    for file_path in [config_file_path+'1.txt', config_file_path+'2.txt', config_file_path+'3.txt', config_file_path+'4.txt', config_file_path+'5.txt']:
        search_engine.add_corpus(file_path)

    while True:
        query = input()
        results = search_engine.search(query)
        print('found {} result(s):'.format(len(results)))
        for result in results:
            print(result)