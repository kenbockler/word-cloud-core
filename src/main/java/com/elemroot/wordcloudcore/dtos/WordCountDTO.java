// Antud klassi saab kasutada JSON-vormingus andmete edastamiseks.

// Kui Word klassi andmed tuleb teisendada WordCountDTO-ks enne
// klientidele saatmist, on see tehtud õigesti.
package com.elemroot.wordcloudcore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCountDTO {
    private UUID id;
    private Map<String, Integer> wordCounts;
}