#ifndef TEMPLATEALGORITHMCREATOR_H
#define TEMPLATEALGORITHMCREATOR_H

#include "AlgorithmCreator.h"
#include "Implementation.h"

template<Implementation<Algorithm> T>
class TemplateAlgorithmCreator final : public AlgorithmCreator {
public:
    std::shared_ptr<Algorithm>
    create(const Matrix &a, const Matrix &b, const int &m, const int &n, const int &q) override {
        return std::make_shared<T>(a, b, m, n, q);
    }
};

#endif //TEMPLATEALGORITHMCREATOR_H
