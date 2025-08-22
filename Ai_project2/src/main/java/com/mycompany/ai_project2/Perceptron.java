package com.mycompany.ai_project2;

import java.util.Random;

public class Perceptron {
  public double[] weights;
   public double bias;
   public double learningRate ;
    int epochs;

    public Perceptron(int inputSize, double learningRate) {
        this.weights = new double[inputSize];
        this.bias = 0;
        this.learningRate = learningRate;
        Random rand = new Random();

    
        for (int i = 0; i < inputSize; i++) {
            weights[i] = rand.nextDouble() - 0.5;
        }
        bias = rand.nextDouble() - 0.5;
    }

    // (Sigmoid)
    private double activation(double sum) {
        return 1 / (1 + Math.exp(-sum));
    }

    // Training the perceptron 
    public void train(double[][] inputs, double[] outputs, int maxEpochs, double SSE) {
        for (epochs = 0; epochs < maxEpochs; epochs++) {
            double totalError = 0;
            for (int i = 0; i < inputs.length; i++) {
                double sum = bias;
                for (int j = 0; j < weights.length; j++) {
                    sum += weights[j] * inputs[i][j];
                }
                double output = activation(sum);
                double error = outputs[i] - output;
                totalError += Math.pow(error, 2);

                // Update weights and bias
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * inputs[i][j];
                }
                bias += learningRate * error;
            }
            
            if (totalError < SSE) break;
        }
    }

    

   
}
