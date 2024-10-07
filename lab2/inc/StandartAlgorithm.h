#ifndef STANDARTALGORITHM_H
#define STANDARTALGORITHM_H

#include "Algorithm.h"

class StandartAlgorithm : public Algorithm {
public:
    StandartAlgorithm(const Matrix &, const Matrix &, const int &, const int &, const int &);

    Matrix execute() override;
};

#endif //STANDARTALGORITHM_H
