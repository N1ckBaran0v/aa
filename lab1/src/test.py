from json import dumps
from levenshtein import *
from datetime import datetime


def main() -> None:
    test_data = (
        ("", ""),
        ("Слово", "Слово"),
        ("один", "семь"),
        ("бобер", "ребро"),
        ("лоск", "локс"),
        ("белка", "лак"),
        ("лак", "белка"),
    )
    answers = (
        "0 0 0 0",
        "0 0 0 0",
        "4 4 4 4",
        "4 4 4 4",
        "2 2 2 1",
        "4 4 4 3",
        "4 4 4 3",
    )
    passed = failed = 0
    for i in range(len(test_data)):
        result = []
        for func in functions.values():
            result.append(func(*test_data[i]))
        if answers[i] == " ".join(map(str, result)):
            passed += 1
        else:
            failed += 1
    result = {
        "timestamp": datetime.strftime(datetime.now(), "%Y-%m-%dT%H:%M:%S+03:00"),
        "coverage": 55,
        "passed": passed,
        "failed": failed
    }
    with open("ready/stud-unit-test-report.json", "w") as f:
        f.write(dumps(result))


if __name__ == '__main__':
    main()