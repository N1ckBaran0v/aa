#include <cstdlib>
#include <ctime>
#include <iostream>

#include "Benchmark.h"
#include "AlgorithmCreatorsBuilder.h"

#define ITER_COUNT 10000

std::wstring generate(int);
void benchTime(const std::wstring& , const std::shared_ptr<AlgorithmCreator>&, const std::vector<int>&);

void benchmark(const std::vector<int>& lengths) {
    srand(time(0));
    auto algorithms = AlgorithmCreatorsBuilder::getCreators();
    for (const auto& box : algorithms) {
        benchTime(box.first, box.second, lengths);
    }
}

std::wstring generate(int length) {
    auto result = std::wstring();
    result.resize(length);
    for (auto& symbol : result) {
        symbol = L'a' + (rand() % 26);
    }
    return result;
}

void benchTime(const std::wstring& name, const std::shared_ptr<AlgorithmCreator>& creator, const std::vector<int>& lengths) {
    std::wcout << name << std::endl;
    auto results = std::vector<timespec>(lengths.size());
    auto start = timespec();
    auto end = timespec();
    for (auto i = 0; i < lengths.size(); ++i) {
        auto length = lengths[i];
        std::wcout << L"Длина: " << length << std::endl;
        results[i].tv_sec = 0;
        results[i].tv_nsec = 0;
        for (auto j = 0; j < ITER_COUNT; ++j) {
            std::wcout << L"[" << j << L"/" << ITER_COUNT << L"]\r";
            std::wcout.flush();
            auto algorithm = creator->create(generate(length), generate(length));
            clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &start);
            algorithm->execute();
            clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &end);
            results[i].tv_sec += end.tv_sec - start.tv_sec;
            results[i].tv_nsec += end.tv_nsec - start.tv_nsec;
            results[i].tv_sec += results[i].tv_nsec / 1000000000;
            results[i].tv_nsec %= 1000000000;
        }
        std::wcout << L"[" << ITER_COUNT << L"/" << ITER_COUNT << L"]\n";
    }
    std::wcout << L"Результаты:\n[";
    for (auto i = 0; i < lengths.size(); ++i) {
        std::wcout.setf(std::ios::fixed);
        std::wcout.precision(9);
        std::wcout << (results[i].tv_sec + (results[i].tv_nsec * 1e-9)) / ITER_COUNT;
        if (i + 1 != lengths.size()) {
            std::wcout << L", ";
        }
    }
    std::wcout << L"]\n";
}