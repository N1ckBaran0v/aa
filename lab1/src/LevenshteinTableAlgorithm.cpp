#include <algorithm>

#include "LevenshteinTableAlgorithm.h"

LevenshteinTableAlgorithm::LevenshteinTableAlgorithm(const std::wstring& first, const std::wstring& second) : 
    Algorithm(first, second) {}

int LevenshteinTableAlgorithm::execute() {
    auto s1 = first.size() + 1;
    auto s2 = second.size() + 1;
    table = initMatrix(s1, s2);
    for (auto i = 0; i < s1; ++i) {
        for (auto j = 0; j < s2; ++j) {
            table[i][j] = i + j;
            if (i > 0 && j > 0) {
                table[i][j] = std::min({table[i - 1][j] + 1, table[i][j - 1] + 1, 
                    table[i - 1][j - 1] + (first[i - 1] != second[j - 1])});
            }
        }
    }
    if (isNeedPrintMatrix()) {
        printMatrix(table);
    } 
    return table[first.size()][second.size()];
}