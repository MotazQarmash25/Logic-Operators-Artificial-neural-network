# 🧠 Artificial Neural Network – Logic Operators  

## 📖 Project Overview  
This project is part of **Assignment #2** for the Artificial Neural Networks course.  
The chosen task is **Task #1: Logic Operators**, where a neural network is built to emulate the behavior of basic logical gates (AND, OR, NOT, XOR, etc.).  

The project implements:  
- A **neural network model** (single perceptron or multi-layer network depending on the gate).  
- A **backpropagation learning algorithm** for training.  
- A **GUI** that allows users to:  
  - Select a logical gate (AND, OR, NOT, XOR, etc.).  
  - Train the neural network accordingly.  
  - Visualize the classification regions after training.  

---

## ⚙️ Methodology  

1. **Initialization**  
   - Random initialization of weights and biases.  

2. **Network Structure**  
   - Choice between perceptron (for linearly separable gates like AND, OR)  
   - Multi-layer network (for XOR which is not linearly separable).  

3. **Training**  
   - Learning algorithm: **Backpropagation**  
   - Training optimization: **Gradient Descent**  
   - Activation functions: Sigmoid / ReLU (depending on implementation).  
   - Performance metrics: **SSE (Sum of Squared Errors)** or **MSE (Mean Squared Error)**.  

4. **Graphical User Interface (GUI)**  
   - Dropdown menu to select logical gate.  
   - Train button to start training.  
   - Graph showing classification boundaries (decision regions).  

---

## 🧮 Example: Logic Gate Truth Tables  

**AND Gate**  
| Input A | Input B | Output |  
|---------|---------|--------|  
| 0       | 0       | 0      |  
| 0       | 1       | 0      |  
| 1       | 0       | 0      |  
| 1       | 1       | 1      |  

**OR Gate**  
| Input A | Input B | Output |  
|---------|---------|--------|  
| 0       | 0       | 0      |  
| 0       | 1       | 1      |  
| 1       | 0       | 1      |  
| 1       | 1       | 1      |  

**XOR Gate** (needs MLP)  
| Input A | Input B | Output |  
|---------|---------|--------|  
| 0       | 0       | 0      |  
| 0       | 1       | 1      |  
| 1       | 0       | 1      |  
| 1       | 1       | 0      |  

---
📘 Notes

Perceptron is sufficient for AND / OR / NOT gates.

XOR requires a multi-layer neural network (MLP).

GUI and visualization implemented with matplotlib / tkinter (or any suitable toolkit).

👨‍🏫 Course Info

Assignment #2 – Artificial Neural Networks

Task #1 Selected: Logic Operators
