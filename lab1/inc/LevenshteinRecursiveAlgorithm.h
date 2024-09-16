#ifndef __LEVENSHTEIN_RECURSIVE_ALGORITHM__
#define __LEVENSHTEIN_RECURSIVE_ALGORITHM__

#include "Algorithm.h"

class LevenshteinRecursiveAlgorithm : public Algorithm {
public:
    LevenshteinRecursiveAlgorithm() = delete;
    LevenshteinRecursiveAlgorithm(const std::wstring&, const std::wstring&);

    int execute() final override;
    
private:
    int count(const int, const int);
};

#endif // __LEVENSHTEIN_RECURSIVE_ALGORITHM__