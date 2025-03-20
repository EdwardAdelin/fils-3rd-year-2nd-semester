import numpy as np
import matplotlib.pyplot as plt
x=np.arange(5)
plt.plot(x)

plt.figure()
plt.plot(x,'.-m')

plt.figure()
y=x**2
plt.plot(x,y)

plt.figure()
y=x**2
plt.stem(x,y)

plt.show()
