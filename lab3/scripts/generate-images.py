import matplotlib.pyplot as plt
from json import load


def main() -> None:
    plot("binary", "Количество сравнений при бинарном поиске")
    plot("linear", "Количество сравнений при линейном поиске")
    plot("sorted", "Количество сравнений при бинарном поиске (в порядке возрастания)")


def plot(filename: str, title: str) -> None:
    with open("../src/main/resources/" + filename + ".json", "r") as file:
        data = load(file)
    x = [f"{item[0]}" for item in data]
    y = [item[1] for item in data]
    plt.bar(x, y, width=0.5)
    forward = lambda arr: arr[1::99]
    plt.xticks(forward(x))
    plt.xlabel("Значение")
    plt.ylabel("Количество сравнений")
    plt.title(title)
    plt.savefig("../docs/tex_parts/" + filename + "_graph.pdf", format="pdf")
    plt.show()


if __name__ == "__main__":
    main()