#ifndef __DAMERAU_LEVENSHTEIN_ALGORITHM__
#define __DAMERAU_LEVENSHTEIN_ALGORITHM__

#include "Algorithm.h"
#include "Matrix.h"

class DamerauLevenshteinAlgorithm : public Algorithm {
public:
    DamerauLevenshteinAlgorithm() = delete;
    DamerauLevenshteinAlgorithm(const std::wstring&, const std::wstring&);

    int execute() final override;
    
private:
    Matrix table;
};

#endif // __DAMERAU_LEVENSHTEIN_ALGORITHM__