#ifndef STANDARTOPTIMISEDALGORITHM_H
#define STANDARTOPTIMISEDALGORITHM_H

#include "StandartAlgorithm.h"

class StandartOptimisedAlgorithm final : public StandartAlgorithm {
public:
    StandartOptimisedAlgorithm(const Matrix &, const Matrix &, const int &, const int &, const int &);

    Matrix execute() override;
};

#endif //STANDARTOPTIMISEDALGORITHM_H
