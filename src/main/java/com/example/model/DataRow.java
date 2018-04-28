package com.example.model;


import com.example.util.StringUtil;

import java.util.HashMap;

/**
 * 描述: 单个结果集对象
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/26 09:54
 */
@Deprecated
public class DataRow extends HashMap<String, Object>{

    public void set(String name, String value) {
        if (StringUtil.isNotEmpty(name)) {
            if (value == null) {
                this.put(name, "");
            } else {
                this.put(name, value);
            }

        }
    }

    public void set(String name, int value) {
        this.put(name, new Integer(value));
    }

    public void set(String name, boolean value) {
        this.put(name, new Boolean(value));
    }

    public void set(String name, long value) {
        this.put(name, new Long(value));
    }

    public void set(String name, float value) {
        this.put(name, new Float(value));
    }

    public void set(String name, double value) {
        this.put(name, new Double(value));
    }

    public void set(String name, Object value) {
        this.put(name, value);
    }

    public String getString(String name) {
        if (StringUtil.isNotEmpty(name)) {
            Object obj = this.get(name);
            return obj == null ? "" : obj.toString();
        } else {
            return "";
        }
    }

    public int getInt(String name) {
        if (StringUtil.isNotEmpty(name)) {
            //int value = false;
            if (!this.containsKey(name)) {
                return 0;
            } else {
                Object obj = this.get(name);
                if (obj == null) {
                    return 0;
                } else {
                    int value;
                    if (!(obj instanceof Integer)) {
                        try {
                            value = Integer.parseInt(obj.toString());
                        } catch (Exception var5) {
                            value = 0;
                        }
                    } else {
                        value = ((Integer)obj).intValue();
                       // obj = null;
                    }

                    return value;
                }
            }
        } else {
            return 0;
        }
    }

    public long getLong(String name) {
        if (StringUtil.isNotEmpty(name)) {
            long value = 0L;
            if (!this.containsKey(name)) {
                return 0L;
            } else {
                Object obj = this.get(name);
                if (obj == null) {
                    return 0L;
                } else {
                    if (!(obj instanceof Long)) {
                        try {
                            value = Long.parseLong(obj.toString());
                        } catch (Exception var6) {
                            value = 0L;
                        }
                    } else {
                        value = ((Long)obj).longValue();
                        obj = null;
                    }

                    return value;
                }
            }
        } else {
            return 0L;
        }
    }

    public float getFloat(String name) {
        if (StringUtil.isNotEmpty(name)) {
            float value = 0.0F;
            if (!this.containsKey(name)) {
                return 0.0F;
            } else {
                Object obj = this.get(name);
                if (obj == null) {
                    return 0.0F;
                } else {
                    if (!(obj instanceof Float)) {
                        try {
                            value = Float.parseFloat(obj.toString());
                        } catch (Exception var5) {
                            value = 0.0F;
                        }
                    } else {
                        value = ((Float)obj).floatValue();
                        obj = null;
                    }

                    return value;
                }
            }
        } else {
            return 0.0F;
        }
    }

    public double getDouble(String name) {
        if (StringUtil.isNotEmpty(name)) {
            double value = 0.0D;
            if (!this.containsKey(name)) {
                return 0.0D;
            } else {
                Object obj = this.get(name);
                if (obj == null) {
                    return 0.0D;
                } else {
                    if (!(obj instanceof Double)) {
                        try {
                            value = Double.parseDouble(obj.toString());
                        } catch (Exception var6) {
                            value = 0.0D;
                        }
                    } else {
                        value = ((Double)obj).doubleValue();
                        obj = null;
                    }

                    return value;
                }
            }
        } else {
            return 0.0D;
        }
    }

    public boolean getBoolean(String name) {
        if (StringUtil.isNotEmpty(name)) {
            boolean value = false;
            if (!this.containsKey(name)) {
                return false;
            } else {
                Object obj = this.get(name);
                if (obj == null) {
                    return false;
                } else if (obj instanceof Boolean) {
                    return ((Boolean)obj).booleanValue();
                } else {
                    value = Boolean.valueOf(obj.toString()).booleanValue();
                    obj = null;
                    return value;
                }
            }
        } else {
            return false;
        }
    }

    public Object getObject(String name) {
        if (StringUtil.isNotEmpty(name)) {
            return !this.containsKey(name) ? null : this.get(name);
        } else {
            return null;
        }
    }




}
