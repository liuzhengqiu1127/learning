import yaml
import os

def get_yaml_data(yaml_file):
    print("===get yaml file data")
    file = open(yaml_file, 'r', encoding="utf-8")
    file_data = file.read()
    file.close()

    print(file_data)
    print("type:", type(file_data))

    print("change yaml data to dict or list")
    data = yaml.load(file_data)
    print(data)
    print("type:", type(data))
    return data

if __name__ == '__main__':
    current_path = os.path.abspath("..") + os.path.sep + "file"
    yaml_path = os.path.join(current_path, "config.yaml")
    get_yaml_data(yaml_path)