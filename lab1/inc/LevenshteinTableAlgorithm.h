#ifndef __LEVENSHTEIN_TABLE_ALGORITHM__
#define __LEVENSHTEIN_TABLE_ALGORITHM__

#include "Algorithm.h"
#include "Matrix.h"

class LevenshteinTableAlgorithm : public Algorithm {
public:
    LevenshteinTableAlgorithm() = delete;
    LevenshteinTableAlgorithm(const std::wstring&, const std::wstring&);

    int execute() final override;
    
private:
    Matrix table;
};

#endif // __LEVENSHTEIN_TABLE_ALGORITHM__