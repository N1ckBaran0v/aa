#include "VinogradOptimisedAlgorithm.h"

VinogradOptimisedAlgorithm::VinogradOptimisedAlgorithm(const Matrix &a, const Matrix &b, const int &m, const int &n,
                                                       const int &q) : VinogradAlgorithm(a, b, m, n, q) {
}

Matrix VinogradOptimisedAlgorithm::execute() {
    auto c = initMatrix(m, q);
    for (auto i = 0; i < m; ++i) {
        if (n > 1) {
            mulR[i] = a[i][0] * a[i][1];
        } else {
            mulR[i] = 0;
        }
        for (auto k = 1; k < n / 2; ++k) {
            mulR[i] += a[i][k << 1] * a[i][(k << 1) + 1];
        }
    }
    for (auto j = 0; j < q; ++j) {
        if (n > 1) {
            mulC[j] = b[0][j] * b[1][j];
        } else {
            mulC[j] = 0;
        }
        for (auto k = 1; k < n / 2; ++k) {
            mulC[j] += b[k << 1][j] * b[(k << 1) + 1][j];
        }
    }
    for (auto i = 0; i < m; ++i) {
        for (auto j = 0; j < q; ++j) {
            if (n > 1) {
                c[i][j] = (a[i][0] + b[1][j]) * (a[i][1] + b[0][j]) - mulR[i] - mulC[j];
            } else {
                c[i][j] = -mulR[i] - mulC[j];
            }
            for (auto k = 1; k < n / 2; ++k) {
                c[i][j] += (a[i][k << 1] + b[(k << 1) + 1][j]) * (a[i][(k << 1) + 1] + b[k << 1][j]);
            }
            if (n % 2 == 1) {
                c[i][j] += a[i][n - 1] * b[n - 1][j];
            }
        }
    }
    return c;
}
