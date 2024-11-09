import sys
from levenshtein import *


def main():
    sys.stdout.write("Введите первое слово: ")
    sys.stdout.flush()
    a = sys.stdin.readline().strip()
    sys.stdout.write("Введите второе слово: ")
    sys.stdout.flush()
    b = sys.stdin.readline().strip()
    for unlocalized in functions:
        sys.stdout.write(f"{local_dict["russian"][unlocalized]}: {functions[unlocalized](a, b)}\n")

if __name__ == '__main__':
    main()
