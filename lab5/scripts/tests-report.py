from json import dumps
from re import search
from datetime import datetime


def main() -> None:
    with open("build/jacocoHtml/index.html", "r", encoding="utf-8") as f:
        html = f.readline()
        span = search("[0-9]+%", html).span()
        coverage = int(html[span[0]:span[1] - 1])
    with open("scripts/tests.txt", "r", encoding="utf-8") as f:
        passed = failed = 0
        for line in f:
            line.strip()
            if "Test" in line:
                if "PASSED" in line:
                    passed += 1
                if "FAILED" in line:
                    failed += 1
    result = {
        "timestamp": datetime.strftime(datetime.now(), "%Y-%m-%dT%H:%M:%S+03:00"),
        "coverage": coverage,
        "passed": passed,
        "failed": failed
    }
    with open("ready/stud-unit-test-report.json", "w") as f:
        f.write(dumps(result))


if __name__ == '__main__':
    main()
