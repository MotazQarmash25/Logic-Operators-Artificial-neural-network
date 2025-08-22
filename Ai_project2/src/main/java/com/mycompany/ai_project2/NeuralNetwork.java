package com.mycompany.ai_project2;

import java.util.Random;

public class NeuralNetwork {
    private static final int INPUT_NODES = 2;      
    private static final int HIDDEN_NODES = 2;   
    private static final int OUTPUT_NODES = 1; 
    private static final double LEARNING_RATE = 0.8;  
    private static final int MAX_EPOCHS = 1000000; 
    private static final double TARGET_SSE = 0.02; 

    public double[][] inputWeights = new double[INPUT_NODES][HIDDEN_NODES];
    public double[] hiddenBiases = new double[HIDDEN_NODES];
    private double[][] outputWeights = new double[HIDDEN_NODES][OUTPUT_NODES];
    private double[] outputBiases = new double[OUTPUT_NODES];
    private Random random = new Random();
 int epochs;
 
    public NeuralNetwork() {
        initializeWeights();
    }

 
   private void initializeWeights() {
    for (int i = 0; i < INPUT_NODES; i++) {
        for (int j = 0; j < HIDDEN_NODES; j++) {
            inputWeights[i][j] = (random.nextDouble() * 2.4) - 1.2;
        }
    }
    for (int j = 0; j < HIDDEN_NODES; j++) {
        hiddenBiases[j] = (random.nextDouble() * 2.4) - 1.2;
    }
    for (int j = 0; j < HIDDEN_NODES; j++) {
        for (int k = 0; k < OUTPUT_NODES; k++) {
            outputWeights[j][k] = (random.nextDouble() * 2.4) - 1.2;
        }
    }
    for (int k = 0; k < OUTPUT_NODES; k++) {
        outputBiases[k] = (random.nextDouble() * 2.4) - 1.2;
    }
}


  
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }


    private double sigmoidDerivative(double x) {
        return x * (1.0 - x);
    }

    // y actual
    private double[] feedForward(double[] input) {
        double[] hiddenLayerOutputs = new double[HIDDEN_NODES];
        double[] outputLayerOutputs = new double[OUTPUT_NODES];

      
        for (int j = 0; j < HIDDEN_NODES; j++) {
            double sum = hiddenBiases[j];
            for (int i = 0; i < INPUT_NODES; i++) {
                sum += input[i] * inputWeights[i][j];
            }
            hiddenLayerOutputs[j] = sigmoid(sum);
        }

     
        for (int k = 0; k < OUTPUT_NODES; k++) {
            double sum = outputBiases[k];
            for (int j = 0; j < HIDDEN_NODES; j++) {
                sum += hiddenLayerOutputs[j] * outputWeights[j][k];
            }
            outputLayerOutputs[k] = sigmoid(sum);
        }

        return outputLayerOutputs;
    }

    
    //  backpropagation
    private void backpropagate(double[] input, double[] output) {
        // Feedforward
        double[] hiddenLayerOutputs = new double[HIDDEN_NODES];
        double[] outputLayerOutputs = feedForward(input);

      
        for (int j = 0; j < HIDDEN_NODES; j++) {
            double sum = hiddenBiases[j];
            for (int i = 0; i < INPUT_NODES; i++) {
                sum += input[i] * inputWeights[i][j];
            }
            hiddenLayerOutputs[j] = sigmoid(sum);
        }

        //  errors 
        double[] outputErrors = new double[OUTPUT_NODES];
        for (int k = 0; k < OUTPUT_NODES; k++) {
            outputErrors[k] = output[k] - outputLayerOutputs[k];
        }

        for (int k = 0; k < OUTPUT_NODES; k++) {
            double delta = outputErrors[k] * sigmoidDerivative(outputLayerOutputs[k]);
            for (int j = 0; j < HIDDEN_NODES; j++) {
                outputWeights[j][k] += LEARNING_RATE * delta * hiddenLayerOutputs[j];
            }
            outputBiases[k] += LEARNING_RATE * delta;
        }

        // Update 
        for (int j = 0; j < HIDDEN_NODES; j++) {
            double delta = 0;
            for (int k = 0; k < OUTPUT_NODES; k++) {
                delta += outputErrors[k] * sigmoidDerivative(outputLayerOutputs[k]) * outputWeights[j][k];
            }
            delta *= sigmoidDerivative(hiddenLayerOutputs[j]);
            for (int i = 0; i < INPUT_NODES; i++) {
                inputWeights[i][j] += LEARNING_RATE * delta * input[i];
            }
            hiddenBiases[j] += LEARNING_RATE * delta;
        }
    }

    
    
    //  SSE 
    private double calculateSSE(double[][] inputs, double[][] targets) {
        double sse = 0.0;
        for (int i = 0; i < inputs.length; i++) {
            double[] output = feedForward(inputs[i]);
            for (int k = 0; k < OUTPUT_NODES; k++) {
                sse += Math.pow(targets[i][k] - output[k], 2);
            }
        }
        return sse;
    }

    
    
    // Train 
    public void train(double[][] inputs, double[][] output) {
        for (int epoch = 0; epoch < MAX_EPOCHS; epoch++) {
            for (int i = 0; i < inputs.length; i++) {
                backpropagate(inputs[i], output[i]);
            }
            double sse = calculateSSE(inputs, output);
           
            if (sse <= TARGET_SSE) {
           
               epochs = epoch;
                break;
            }
        }
  
    }
   
}
