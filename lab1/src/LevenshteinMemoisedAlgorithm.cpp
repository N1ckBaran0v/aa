#include <algorithm>

#include "LevenshteinMemoisedAlgorithm.h"

LevenshteinMemoisedAlgorithm::LevenshteinMemoisedAlgorithm(const std::wstring& first, const std::wstring& second) : 
    Algorithm(first, second) {}

int LevenshteinMemoisedAlgorithm::execute() {
    cache = initMatrix(first.size() + 1, second.size() + 1);
    auto result = count(first.size(), second.size());
    if (isNeedPrintMatrix()) {
        printMatrix(cache);
    } 
    return result;
}

int LevenshteinMemoisedAlgorithm::count(const int s1, const int s2) {
    if (cache[s1][s2] == -1) {
        cache[s1][s2] = s1 + s2;
        if (s1 > 0 && s2 > 0) {
            cache[s1][s2] = std::min({count(s1 - 1, s2) + 1, count(s1, s2 - 1) + 1, 
                count(s1 - 1, s2 - 1) + (first[s1 - 1] != second[s2 - 1])});
        }
    }
    return cache[s1][s2];
}