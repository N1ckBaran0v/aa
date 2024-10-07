#ifndef ALGORITHMCREATOR_H
#define ALGORITHMCREATOR_H

#include <memory>

#include "Algorithm.h"

class AlgorithmCreator {
public:
    virtual ~AlgorithmCreator() = default;

    virtual std::shared_ptr<Algorithm> create(const Matrix &a, const Matrix &b, const int &m, const int &n,
                                              const int &q) = 0;
};

#endif //ALGORITHMCREATOR_H
