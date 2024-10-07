#include "VinogradAlgorithm.h"

VinogradAlgorithm::VinogradAlgorithm(const Matrix &a, const Matrix &b, const int &m, const int &n,
                                     const int &q) : Algorithm(a, b, m, n, q) {
    mulR.resize(m);
    mulC.resize(q);
}

Matrix VinogradAlgorithm::execute() {
    auto c = initMatrix(m, q);
    for (auto i = 0; i < m; ++i) {
        mulR[i] = 0;
        for (auto k = 0; k < n / 2; ++k) {
            mulR[i] = mulR[i] + a[i][2 * k] * a[i][2 * k + 1];
        }
    }
    for (auto j = 0; j < q; ++j) {
        mulC[j] = 0;
        for (auto k = 0; k < n / 2; ++k) {
            mulC[j] = mulC[j] + b[2 * k][j] * b[2 * k + 1][j];
        }
    }
    for (auto i = 0; i < m; ++i) {
        for (auto j = 0; j < q; ++j) {
            c[i][j] = -mulR[i] - mulC[j];
            for (auto k = 0; k < n / 2; ++k) {
                c[i][j] = c[i][j] + (a[i][2 * k] + b[2 * k + 1][j]) * (a[i][2 * k + 1] + b[2 * k][j]);
            }
            if (n % 2 == 1) {
                c[i][j] = c[i][j] + a[i][n - 1] * b[n - 1][j];
            }
        }
    }
    return c;
}
