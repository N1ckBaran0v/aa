#include "StandartOptimisedAlgorithm.h"

StandartOptimisedAlgorithm::StandartOptimisedAlgorithm(const Matrix &a, const Matrix &b, const int &m, const int &n,
                                                       const int &q) : StandartAlgorithm(a, b, m, n, q) {
}

Matrix StandartOptimisedAlgorithm::execute() {
    auto c = initMatrix(m, q);
    for (auto i = 0; i < m; ++i) {
        for (auto j = 0; j < q; ++j) {
            c[i][j] = a[i][0] * b[0][j];
            for (auto k = 1; k < n; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}
