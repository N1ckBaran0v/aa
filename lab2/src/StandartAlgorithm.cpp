#include "StandartAlgorithm.h"

StandartAlgorithm::StandartAlgorithm(const Matrix &a, const Matrix &b, const int &m, const int &n,
                                     const int &q) : Algorithm(a, b, m, n, q) {
}

Matrix StandartAlgorithm::execute() {
    auto c = initMatrix(m, q);
    for (auto i = 0; i < m; ++i) {
        for (auto j = 0; j < q; ++j) {
            c[i][j] = 0;
            for (auto k = 0; k < n; ++k) {
                c[i][j] = c[i][j] + a[i][k] * b[k][j];
            }
        }
    }
    return c;
}
