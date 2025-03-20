import numpy as np
import matplotlib.pyplot as plt

fs = 8000  # Sampling rate (Hz)
f = 4000   # Frequency of sine wave (Hz)
t = np.arange(0, 1, 1/fs)  # Time vector for 1 second
signal = np.sin(2 * np.pi * f * t)  # 4 kHz sine wave

plt.plot(t[:200], signal[:200])  # Show only first 200 samples
plt.xlabel("Time (s)")
plt.ylabel("Amplitude")
plt.title("4 kHz Sine Wave Sampled at 8 kHz")
plt.show()
