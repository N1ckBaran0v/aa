#ifndef ALGORITHMCREAATORSBUILDER_H
#define ALGORITHMCREAATORSBUILDER_H

#include "AlgorithmCreator.h"

class AlgorithmCreatorsBuilder {
public:
    AlgorithmCreatorsBuilder() = delete;

    static std::vector<std::pair<std::wstring, std::shared_ptr<AlgorithmCreator> > > getCreators();
};

#endif //ALGORITHMCREAATORSBUILDER_H
