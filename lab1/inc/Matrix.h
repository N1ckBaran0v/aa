#ifndef __MATRIX_H__
#define __MATRIX_H__

#include <vector>

using Matrix = std::vector<std::vector<int>>;

Matrix initMatrix(const size_t, const size_t);
void printMatrix(const Matrix&);
void setNeedPrintMatrix(const bool);
bool isNeedPrintMatrix();

#endif // __MATRIX_H__