import matplotlib.pyplot as plt


def main() -> None:
    benchmark = {
        "Метод полного перебора": [2.8848100000298454E-6, 1.2544500000063202E-5, 2.1306987000032824E-4, 0.003574800900005415, 0.36511602736034315],
        "Муравьиный алгоритм": [6.69963400008859E-5, 2.1188548000102396E-4, 5.701703800034859E-4, 8.3798283000876E-4, 0.0013840127000108672]
    }
    ticks = [i for i in range(2, 11, 2)]
    index = 0
    styles = ["^", "o"]
    for key in benchmark:
        plt.plot(ticks, benchmark[key], label=key, marker=styles[index])
        index += 1
    plt.xlabel("Количество городов, шт.")
    plt.ylabel("Время работы, с.")
    plt.savefig("../report/inc/img/benchmark.pdf", format="pdf")
    plt.show()


if __name__ == '__main__':
    main()