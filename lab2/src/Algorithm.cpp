#include "Algorithm.h"

Algorithm::Algorithm(const Matrix &a, const Matrix &b, const int &m, const int &n, const int &q) : a(a), b(b), m(m),
    n(n), q(q) {
    if (m < 1 || n < 1 || q < 1) {
        throw std::invalid_argument("Algorithm: invalid arguments");
    }
    if (a.size() != m || b.size() != n) {
        throw std::invalid_argument("Algorithm: invalid arguments");
    }
    for (auto i = 0; i < m; ++i) {
        if (a[i].size() != n) {
            throw std::invalid_argument("Algorithm: invalid arguments");
        }
    }
    for (auto i = 0; i < n; ++i) {
        if (b[i].size() != q) {
            throw std::invalid_argument("Algorithm: invalid arguments");
        }
    }
}
