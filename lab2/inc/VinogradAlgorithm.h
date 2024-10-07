#ifndef VINOGRADALGORITHM_H
#define VINOGRADALGORITHM_H

#include "Algorithm.h"

class VinogradAlgorithm : public Algorithm {
public:
    VinogradAlgorithm(const Matrix &, const Matrix &, const int &, const int &, const int &);

    Matrix execute() override;

protected:
    std::vector<int> mulR, mulC;
};

#endif //VINOGRADALGORITHM_H
