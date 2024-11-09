# import utime
# import urandom
# import usys


def levenshteinRecursive(a, b):
    result = len(a) + len(b)
    if len(a) > 0 and len(b) > 0:
        result = min(levenshteinRecursive(a[:-1], b) + 1,
                     levenshteinRecursive(a, b[:-1]) + 1,
                     levenshteinRecursive(a[:-1], b[:-1]) + (a[-1] != b[-1]))
    return result


def levenshteinMemoised(a, b, cache = None):
    if cache is None:
        cache = [[-1 for j in range(len(b) + 1)] for i in range(len(a) + 1)]
    result = len(a) + len(b)
    if cache[len(a)][len(b)] != -1:
        result = cache[len(a)][len(b)]
    elif len(a) > 0 and len(b) > 0:
        result = min(levenshteinMemoised(a[:-1], b, cache) + 1,
                     levenshteinMemoised(a, b[:-1], cache) + 1,
                     levenshteinMemoised(a[:-1], b[:-1], cache) + (a[-1] != b[-1]))
    cache[len(a)][len(b)] = result
    return result


def levenshteinTable(a, b):
    table = [[i + j for j in range(len(b) + 1)] for i in range(len(a) + 1)]
    for i in range(1, len(a) + 1):
        for j in range(1, len(b) + 1):
            table[i][j] = min(table[i - 1][j] + 1,
                              table[i][j - 1] + 1,
                              table[i - 1][j - 1] + (a[i - 1] != b[j - 1]))
    return table[-1][-1]


def damerauLevenshtein(a, b):
    table = [[i + j for j in range(len(b) + 1)] for i in range(len(a) + 1)]
    for i in range(1, len(a) + 1):
        for j in range(1, len(b) + 1):
            table[i][j] = min(table[i - 1][j] + 1,
                              table[i][j - 1] + 1,
                              table[i - 1][j - 1] + (a[i - 1] != b[j - 1]))
            if i > 1 and j > 1 and a[i - 1] == b[j - 2] and a[i - 2] == b[j - 1]:
                table[i][j] = min(table[i][j], table[i - 2][j - 2] + 1)
    return table[-1][-1]


functions = {"levRec": levenshteinRecursive,
             "levMem": levenshteinMemoised,
             "levTab": levenshteinTable,
             "damLev": damerauLevenshtein}


local_dict = {
    "russian": {
        "levRec": "Рекурсивный алгоритм поиска расстояния Левенштейна",
        "levMem": "Рекурсивный алгоритм поиска расстояния Левенштейна с мемоизацией",
        "levTab": "Матричный алгоритм поиска расстояния Левенштейна",
        "damLev": "Алгоритм поиска расстояния Дамерау-Левенштейна",
    },
    "english": {
        "levRec": "Recursive Levenshtein",
        "levMem": "Recursive Memoised Levenshtein",
        "levTab": "Table Levenshtein",
        "damLev": "Damerau-Levenshtein",
    }
}

# def benchmark():
#     n = 100
#     sizes = [1, 2, 3, 4, 5]
#     usys.stdout.write("{\n")
#     for name in functions:
#         usys.stdout.write("\t\"%s\": " % local_dict["english"][name])
#         results = []
#         for size in sizes:
#             time_sum = 0
#             for _ in range(n):
#                 a = generate(size)
#                 b = generate(size)
#                 start = utime.ticks_us()
#                 functions[name](a, b)
#                 stop = utime.ticks_us()
#                 time_sum += utime.ticks_diff(stop, start)
#             results.append(time_sum // n)
#         usys.stdout.write("[%d, %d, %d, %d, %d],\n" % tuple(results))
#     usys.stdout.write("}")
#
#
# def generate(size):
#     result = ""
#     for _ in range(size):
#         result += urandom.choice("0123456789")
#     return result
#
# if __name__ == '__main__':
#     benchmark()
