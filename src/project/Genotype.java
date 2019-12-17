package project;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Genotype {
    static private Random rand = new Random();
    private static int maxGenValue = 7;
    private List<Integer> genotype = new ArrayList<>();
    private static int genotypesize = 32;

    private Genotype(List<Integer> genotype) {
        this.genotype = genotype;
        this.validGenotype();
    }

    public List<Integer> getGenes() {
        return this.genotype;
    }

    public Genotype makeNewGenotype() {
        List<Integer> newGenotype = new ArrayList<>();
        for (int i = 0; i < genotypesize; i++) {
            newGenotype.add(rand.nextInt(maxGenValue-1));
        }
        return new Genotype(newGenotype);
    }
    public Genotype makeNewGenotypeFromTwo(Genotype firstParent, Genotype secondParent) {
        List<Integer> newGenotype = new ArrayList<>();
        int firstCut = rand.nextInt(genotypesize-1) + 1;
        int secondCut = rand.nextInt(genotypesize-1) +1;
        while(firstCut == secondCut) {
            secondCut = rand.nextInt(genotypesize-1) +1;
    }
        int i=0;
        while(i < firstCut){
            newGenotype.add(firstParent.genotype.get(i));
            i++;
        }
        while(i < secondCut){
            newGenotype.add(firstParent.genotype.get(i));
            i++;
        }
        while(i < genotypesize){
            newGenotype.add(secondParent.genotype.get(i));
            i++;
        }
        return new Genotype(validGenotype(newGenotype));
    }
    private Genotype validGenotype(Genotype genotype) {
        int[] genesCount = new int [maxGenValue+1];
        for (int i = 0; i < maxGenValue+1; i++)
            genesCount[i] = 0;

        for(int i=0; i < genotypesize; i++) {
            genesCount[genotype.genotype.get(i)]++;
        }

        for (int i = 0; i < maxGenValue + 1; i++) {
            if (genesCount[i] == 0) {
                for (int j = 0; j < maxGenValue + 1; j++) {
                    if (genesCount[j] > 1) {
                        genesCount[i] += 1;
                        genesCount[j] -= 1;
                        genotype.genotype.remove(j);
                        genotype.genotype.add(i);
                        break;
                    }
                }
            }
        }
        Arrays.sort(genotype);
        return genotype;
    }
}