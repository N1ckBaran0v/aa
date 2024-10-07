#include <cstring>
#include <iomanip>
#include <iostream>
#include <memory>

#include "AlgorithmCreatorsBuilder.h"
#include "Matrix.h"
#include "StandartAlgorithm.h"

#define ITER_COUNT 1000

void solve();

void benchmark();

void benchmarkConcrete(const std::wstring &, const std::shared_ptr<AlgorithmCreator> &);

int main(int argc, char *argv[]) {
    setlocale(LC_ALL, "");
    if (argc == 2 && strcmp(argv[1], "-r") == 0) {
        solve();
    } else if (argc == 2 && strcmp(argv[1], "-b") == 0) {
        benchmark();
    } else {
        std::cerr << "Usage: " << argv[0] << " <mode>" << std::endl;
        std::cerr << "\t-r - run program" << std::endl;
        std::cerr << "\t-b - run benchmark" << std::endl;
    }
    return 0;
}

void solve() {
    try {
        auto row1 = 0;
        auto col1 = 0;
        readSizes(row1, col1);
        const auto a = readMatrix(row1, col1);
        auto row2 = 0;
        auto col2 = 0;
        readSizes(row2, col2);
        if (col1 != row2) {
            throw std::logic_error("Wrong matrix size");
        }
        const auto b = readMatrix(row2, col2);
        for (auto algorithms = AlgorithmCreatorsBuilder::getCreators(); const auto &[fst, snd]: algorithms) {
            std::wcout << fst << std::endl << snd->create(a, b, row1, col1, col2)->execute();
        }
    } catch ([[maybe_unused]] const std::invalid_argument &e) {
        std::wcout << L"Ошибка: Некорректный размер" << std::endl;
    } catch ([[maybe_unused]] const std::logic_error &e) {
        std::wcout << L"Ошибка: Операция недопустима" << std::endl;
    }
}

void benchmark() {
    srand(time(nullptr));
    for (auto algorithms = AlgorithmCreatorsBuilder::getCreators(); const auto &[fst, snd]: algorithms) {
        benchmarkConcrete(fst, snd);
    }
}

void benchmarkConcrete(const std::wstring &name, const std::shared_ptr<AlgorithmCreator> &creator) {
    auto sizes = std::vector{50, 100, 150, 200, 250, 51, 101, 151, 201, 251};
    auto start = timespec{};
    auto end = timespec{};
    auto [tv_sec, tv_nsec] = timespec{};
    auto times = std::vector<double>{};
    std::wcout << name << std::endl;
    for (const auto &size: sizes) {
        tv_sec = tv_nsec = 0;
        std::wcout << L"Размер: " << size << std::endl;
        for (auto i = 0; i < ITER_COUNT; ++i) {
            std::wcout << L"[" << i << "/" << ITER_COUNT << "]\r";
            std::wcout.flush();
            const auto algorithm = creator->create(generateMatrix(size, size), generateMatrix(size, size), size, size,
                                                   size);
            clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &start);
            algorithm->execute();
            clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &end);
            tv_sec += end.tv_sec - start.tv_sec;
            tv_nsec += end.tv_nsec - start.tv_nsec;
            if (tv_nsec >= 1000000000) {
                tv_sec += 1;
                tv_nsec -= 1000000000;
            }
        }
        std::wcout << L"[" << ITER_COUNT << L"/" << ITER_COUNT << "]" << std::endl;
        times.push_back((tv_sec + tv_nsec / 1e9) / ITER_COUNT);
    }
    for (const auto &time: times) {
        std::wcout.setf(std::ios::fixed);
        std::wcout << std::setprecision(4);
        std::wcout << time << L" ";
    }
    std::wcout << std::endl;
}
