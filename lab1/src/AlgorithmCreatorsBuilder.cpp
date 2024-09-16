#include "AlgorithmCreatorsBuilder.h"
#include "TemplateAlgorithmCreator.h"
#include "LevenshteinMemoisedAlgorithm.h"
#include "LevenshteinRecursiveAlgorithm.h"
#include "LevenshteinTableAlgorithm.h"
#include "DamerauLevenshteinAlgorithm.h"

bool flag = false;

std::vector<std::pair<std::wstring, std::shared_ptr<AlgorithmCreator>>> AlgorithmCreatorsBuilder::getCreators() {
    auto result = std::vector<std::pair<std::wstring, std::shared_ptr<AlgorithmCreator>>>();
    if (!flag) {
        result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator>>
            (L"Рекурсивный алгоритм поиска расстояния Левенштейна", 
            std::make_shared<TemplateAlgorithmCreator<LevenshteinRecursiveAlgorithm>>()));
    }
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator>>
        (L"Рекурсивный алгоритм поиска расстояния Левенштейна с мемоизацией", 
        std::make_shared<TemplateAlgorithmCreator<LevenshteinMemoisedAlgorithm>>()));
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator>>
        (L"Матричный алгоритм поиска расстояния Левенштейна", 
        std::make_shared<TemplateAlgorithmCreator<LevenshteinTableAlgorithm>>()));
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator>>
        (L"Алгоритм поиска расстояния Дамерау-Левенштейна", 
        std::make_shared<TemplateAlgorithmCreator<DamerauLevenshteinAlgorithm>>()));
    return result;
}

void AlgorithmCreatorsBuilder::setExcludeRecursive(bool newValue) {
    flag = newValue;
}

bool AlgorithmCreatorsBuilder::getExcludeRecursive() {
    return flag;
}