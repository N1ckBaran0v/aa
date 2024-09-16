#include <algorithm>

#include "LevenshteinRecursiveAlgorithm.h"

LevenshteinRecursiveAlgorithm::LevenshteinRecursiveAlgorithm(const std::wstring& first, const std::wstring& second) : 
    Algorithm(first, second) {}

int LevenshteinRecursiveAlgorithm::execute() {
    return count(first.size(), second.size());
}

int LevenshteinRecursiveAlgorithm::count(const int s1, const int s2) {
    auto result = s1 + s2;
    if (s1 > 0 && s2 > 0) {
        result = std::min({count(s1 - 1, s2) + 1, count(s1, s2 - 1) + 1, 
            count(s1 - 1, s2 - 1) + (first[s1 - 1] != second[s2 - 1])});
    }
    return result;
}