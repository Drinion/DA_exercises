#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd


# In[2]:


import matplotlib.pyplot as plt


# In[6]:


plt.rcParams["figure.figsize"] = [7.50, 3.50]
plt.rcParams["figure.autolayout"] = True


# In[9]:


df_insertion = pd.read_csv('/Users/dariomarti/Projects/DA_exercises/DA_ex01/time_insertion_sort.csv')


# In[10]:


df_insertion.set_index('#Elements').plot()


# In[11]:


df_merge = pd.read_csv('/Users/dariomarti/Projects/DA_exercises/DA_ex01/time_merge_sort.csv')


# In[12]:


df_merge.set_index('#Elements').plot()


# In[ ]:




