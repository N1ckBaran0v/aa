import matplotlib.pyplot as plt


def main() -> None:
    benchmark = {
	    0: 182.345626,
	    1: 189.211248,
	    2: 303.699231,
	    4: 625.987966,
	    8: 527.789695,
	    16: 316.746739,
    }
    tmp = [i for i in range(len(benchmark))]
    plt.bar(tmp, benchmark.values())
    plt.xticks(tmp, benchmark.keys())
    plt.xlabel("Количество дополнительных потоков")
    plt.ylabel("Среднее количество\nвыгруженных страниц в минуту")
    plt.title("Зависимость производительности программы\nот количества дополнительных потоков")
    plt.savefig("../report/work_graph.pdf", format="pdf")
    plt.show()


if __name__ == '__main__':
    main()