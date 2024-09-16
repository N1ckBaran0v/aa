#ifndef __LEVENSHTEIN_MEMOISED_ALGORITHM_H__
#define __LEVENSHTEIN_MEMOISED_ALGORITHM_H__

#include "Algorithm.h"
#include "Matrix.h"

class LevenshteinMemoisedAlgorithm : public Algorithm {
public:
    LevenshteinMemoisedAlgorithm() = delete;
    LevenshteinMemoisedAlgorithm(const std::wstring&, const std::wstring&);

    int execute() final override;
    
private:
    Matrix cache;
    
    int count(const int, const int);
};

#endif // __LEVENSHTEIN_MEMOISED_ALGORITHM_H__
