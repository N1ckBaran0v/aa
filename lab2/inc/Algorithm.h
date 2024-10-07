#ifndef ALGORITHM_H
#define ALGORITHM_H

#include "Matrix.h"

class Algorithm {
public:
    Algorithm(const Matrix &, const Matrix &, const int &, const int &, const int &);

    virtual ~Algorithm() = default;

    virtual Matrix execute() = 0;

protected:
    Matrix a, b;
    const int m, n, q;
};

#endif //ALGORITHM_H
