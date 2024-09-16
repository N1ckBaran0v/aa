#include <iostream>
#include <string.h>

#include "AlgorithmCreatorsBuilder.h"
#include "Matrix.h"
#include "Benchmark.h"

void execute();
void inputString(std::wstring&, const std::wstring&);
void printUsage();

int main(int argc, char **argv) {
    setlocale(LC_ALL, "");
    if (argc == 2) {
        if (strcmp(argv[1], "-i") == 0) {
            execute();
        } else if (strcmp(argv[1], "-b") == 0) {
            benchmark(std::vector<int>{2, 4, 6, 8});
        } else if (strcmp(argv[1], "-r") == 0) {
            AlgorithmCreatorsBuilder::setExcludeRecursive(true);
            benchmark(std::vector<int>{500, 1000, 1500, 2000, 2500});
        } else {
            printUsage();
        }
    } else {
        printUsage();
    }
    return 0;
}

void execute() {
    setNeedPrintMatrix(true);
    auto first = std::wstring();
    auto second = first;
    inputString(first, L"Введите первую строку: ");
    inputString(second, L"Введите вторую строку: ");
    auto algorithms = AlgorithmCreatorsBuilder::getCreators();
    for (const auto& box : algorithms) {
        std::wcout << box.first << std::endl;
        std::wcout << box.second->create(first, second)->execute() << std::endl;
    }
}

void inputString(std::wstring& result, const std::wstring& message) {
    std::wcout << message;
    std::getline(std::wcin, result);
}

void printUsage() {
    std::wcout << L"Использование: ./lab1.exe <параметр>\n";
    std::wcout << L"\t-i - обычный режим работы;\n";
    std::wcout << L"\t-b - замер времени на небольших строках;\n";
    std::wcout << L"\t-r - замер времени на больших строках;\n";
}