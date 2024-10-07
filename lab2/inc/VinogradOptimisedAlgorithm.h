#ifndef VINOGRADOPTIMISEDALGORITHM_H
#define VINOGRADOPTIMISEDALGORITHM_H

#include "Matrix.h"
#include "VinogradAlgorithm.h"

class VinogradOptimisedAlgorithm final : public VinogradAlgorithm {
public:
    VinogradOptimisedAlgorithm(const Matrix &, const Matrix &, const int &, const int &, const int &);

    Matrix execute() override;
};

#endif //VINOGRADOPTIMISEDALGORITHM_H
