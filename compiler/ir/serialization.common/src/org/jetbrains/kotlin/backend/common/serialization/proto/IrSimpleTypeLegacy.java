// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: compiler/ir/serialization.common/src/KotlinIr.proto

package org.jetbrains.kotlin.backend.common.serialization.proto;

/**
 * Protobuf type {@code org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy}
 */
public final class IrSimpleTypeLegacy extends
    org.jetbrains.kotlin.protobuf.GeneratedMessageLite implements
    // @@protoc_insertion_point(message_implements:org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy)
    IrSimpleTypeLegacyOrBuilder {
  // Use IrSimpleTypeLegacy.newBuilder() to construct.
  private IrSimpleTypeLegacy(org.jetbrains.kotlin.protobuf.GeneratedMessageLite.Builder builder) {
    super(builder);
    this.unknownFields = builder.getUnknownFields();
  }
  private IrSimpleTypeLegacy(boolean noInit) { this.unknownFields = org.jetbrains.kotlin.protobuf.ByteString.EMPTY;}

  private static final IrSimpleTypeLegacy defaultInstance;
  public static IrSimpleTypeLegacy getDefaultInstance() {
    return defaultInstance;
  }

  public IrSimpleTypeLegacy getDefaultInstanceForType() {
    return defaultInstance;
  }

  private final org.jetbrains.kotlin.protobuf.ByteString unknownFields;
  private IrSimpleTypeLegacy(
      org.jetbrains.kotlin.protobuf.CodedInputStream input,
      org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
      throws org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException {
    initFields();
    int mutable_bitField0_ = 0;
    org.jetbrains.kotlin.protobuf.ByteString.Output unknownFieldsOutput =
        org.jetbrains.kotlin.protobuf.ByteString.newOutput();
    org.jetbrains.kotlin.protobuf.CodedOutputStream unknownFieldsCodedOutput =
        org.jetbrains.kotlin.protobuf.CodedOutputStream.newInstance(
            unknownFieldsOutput, 1);
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFieldsCodedOutput,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              annotation_ = new java.util.ArrayList<org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall>();
              mutable_bitField0_ |= 0x00000001;
            }
            annotation_.add(input.readMessage(org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall.PARSER, extensionRegistry));
            break;
          }
          case 16: {
            bitField0_ |= 0x00000001;
            classifier_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000002;
            hasQuestionMark_ = input.readBool();
            break;
          }
          case 32: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              argument_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000008;
            }
            argument_.add(input.readInt64());
            break;
          }
          case 34: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008) && input.getBytesUntilLimit() > 0) {
              argument_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000008;
            }
            while (input.getBytesUntilLimit() > 0) {
              argument_.add(input.readInt64());
            }
            input.popLimit(limit);
            break;
          }
        }
      }
    } catch (org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException(
          e.getMessage()).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        annotation_ = java.util.Collections.unmodifiableList(annotation_);
      }
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        argument_ = java.util.Collections.unmodifiableList(argument_);
      }
      try {
        unknownFieldsCodedOutput.flush();
      } catch (java.io.IOException e) {
      // Should not happen
      } finally {
        unknownFields = unknownFieldsOutput.toByteString();
      }
      makeExtensionsImmutable();
    }
  }
  public static org.jetbrains.kotlin.protobuf.Parser<IrSimpleTypeLegacy> PARSER =
      new org.jetbrains.kotlin.protobuf.AbstractParser<IrSimpleTypeLegacy>() {
    public IrSimpleTypeLegacy parsePartialFrom(
        org.jetbrains.kotlin.protobuf.CodedInputStream input,
        org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
        throws org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException {
      return new IrSimpleTypeLegacy(input, extensionRegistry);
    }
  };

  @java.lang.Override
  public org.jetbrains.kotlin.protobuf.Parser<IrSimpleTypeLegacy> getParserForType() {
    return PARSER;
  }

  private int bitField0_;
  public static final int ANNOTATION_FIELD_NUMBER = 1;
  private java.util.List<org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall> annotation_;
  /**
   * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
   */
  public java.util.List<org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall> getAnnotationList() {
    return annotation_;
  }
  /**
   * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
   */
  public java.util.List<? extends org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCallOrBuilder> 
      getAnnotationOrBuilderList() {
    return annotation_;
  }
  /**
   * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
   */
  public int getAnnotationCount() {
    return annotation_.size();
  }
  /**
   * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
   */
  public org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall getAnnotation(int index) {
    return annotation_.get(index);
  }
  /**
   * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
   */
  public org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCallOrBuilder getAnnotationOrBuilder(
      int index) {
    return annotation_.get(index);
  }

  public static final int CLASSIFIER_FIELD_NUMBER = 2;
  private long classifier_;
  /**
   * <code>required int64 classifier = 2;</code>
   */
  public boolean hasClassifier() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int64 classifier = 2;</code>
   */
  public long getClassifier() {
    return classifier_;
  }

  public static final int HAS_QUESTION_MARK_FIELD_NUMBER = 3;
  private boolean hasQuestionMark_;
  /**
   * <code>required bool has_question_mark = 3;</code>
   */
  public boolean hasHasQuestionMark() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required bool has_question_mark = 3;</code>
   */
  public boolean getHasQuestionMark() {
    return hasQuestionMark_;
  }

  public static final int ARGUMENT_FIELD_NUMBER = 4;
  private java.util.List<java.lang.Long> argument_;
  /**
   * <code>repeated int64 argument = 4 [packed = true];</code>
   *
   * <pre>
   * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
   * </pre>
   */
  public java.util.List<java.lang.Long>
      getArgumentList() {
    return argument_;
  }
  /**
   * <code>repeated int64 argument = 4 [packed = true];</code>
   *
   * <pre>
   * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
   * </pre>
   */
  public int getArgumentCount() {
    return argument_.size();
  }
  /**
   * <code>repeated int64 argument = 4 [packed = true];</code>
   *
   * <pre>
   * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
   * </pre>
   */
  public long getArgument(int index) {
    return argument_.get(index);
  }
  private int argumentMemoizedSerializedSize = -1;

  private void initFields() {
    annotation_ = java.util.Collections.emptyList();
    classifier_ = 0L;
    hasQuestionMark_ = false;
    argument_ = java.util.Collections.emptyList();
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasClassifier()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHasQuestionMark()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getAnnotationCount(); i++) {
      if (!getAnnotation(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(org.jetbrains.kotlin.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    for (int i = 0; i < annotation_.size(); i++) {
      output.writeMessage(1, annotation_.get(i));
    }
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(2, classifier_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBool(3, hasQuestionMark_);
    }
    if (getArgumentList().size() > 0) {
      output.writeRawVarint32(34);
      output.writeRawVarint32(argumentMemoizedSerializedSize);
    }
    for (int i = 0; i < argument_.size(); i++) {
      output.writeInt64NoTag(argument_.get(i));
    }
    output.writeRawBytes(unknownFields);
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < annotation_.size(); i++) {
      size += org.jetbrains.kotlin.protobuf.CodedOutputStream
        .computeMessageSize(1, annotation_.get(i));
    }
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += org.jetbrains.kotlin.protobuf.CodedOutputStream
        .computeInt64Size(2, classifier_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += org.jetbrains.kotlin.protobuf.CodedOutputStream
        .computeBoolSize(3, hasQuestionMark_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < argument_.size(); i++) {
        dataSize += org.jetbrains.kotlin.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(argument_.get(i));
      }
      size += dataSize;
      if (!getArgumentList().isEmpty()) {
        size += 1;
        size += org.jetbrains.kotlin.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      argumentMemoizedSerializedSize = dataSize;
    }
    size += unknownFields.size();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  protected java.lang.Object writeReplace()
      throws java.io.ObjectStreamException {
    return super.writeReplace();
  }

  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(
      org.jetbrains.kotlin.protobuf.ByteString data)
      throws org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(
      org.jetbrains.kotlin.protobuf.ByteString data,
      org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
      throws org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(byte[] data)
      throws org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(
      byte[] data,
      org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
      throws org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(
      java.io.InputStream input,
      org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseDelimitedFrom(
      java.io.InputStream input,
      org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(
      org.jetbrains.kotlin.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parseFrom(
      org.jetbrains.kotlin.protobuf.CodedInputStream input,
      org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }

  /**
   * Protobuf type {@code org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy}
   */
  public static final class Builder extends
      org.jetbrains.kotlin.protobuf.GeneratedMessageLite.Builder<
        org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy, Builder>
      implements
      // @@protoc_insertion_point(builder_implements:org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy)
      org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacyOrBuilder {
    // Construct using org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
    }
    private static Builder create() {
      return new Builder();
    }

    public Builder clear() {
      super.clear();
      annotation_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      classifier_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      hasQuestionMark_ = false;
      bitField0_ = (bitField0_ & ~0x00000004);
      argument_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }

    public org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy getDefaultInstanceForType() {
      return org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy.getDefaultInstance();
    }

    public org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy build() {
      org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy buildPartial() {
      org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy result = new org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        annotation_ = java.util.Collections.unmodifiableList(annotation_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.annotation_ = annotation_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000001;
      }
      result.classifier_ = classifier_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000002;
      }
      result.hasQuestionMark_ = hasQuestionMark_;
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        argument_ = java.util.Collections.unmodifiableList(argument_);
        bitField0_ = (bitField0_ & ~0x00000008);
      }
      result.argument_ = argument_;
      result.bitField0_ = to_bitField0_;
      return result;
    }

    public Builder mergeFrom(org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy other) {
      if (other == org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy.getDefaultInstance()) return this;
      if (!other.annotation_.isEmpty()) {
        if (annotation_.isEmpty()) {
          annotation_ = other.annotation_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureAnnotationIsMutable();
          annotation_.addAll(other.annotation_);
        }
        
      }
      if (other.hasClassifier()) {
        setClassifier(other.getClassifier());
      }
      if (other.hasHasQuestionMark()) {
        setHasQuestionMark(other.getHasQuestionMark());
      }
      if (!other.argument_.isEmpty()) {
        if (argument_.isEmpty()) {
          argument_ = other.argument_;
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          ensureArgumentIsMutable();
          argument_.addAll(other.argument_);
        }
        
      }
      setUnknownFields(
          getUnknownFields().concat(other.unknownFields));
      return this;
    }

    public final boolean isInitialized() {
      if (!hasClassifier()) {
        
        return false;
      }
      if (!hasHasQuestionMark()) {
        
        return false;
      }
      for (int i = 0; i < getAnnotationCount(); i++) {
        if (!getAnnotation(i).isInitialized()) {
          
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        org.jetbrains.kotlin.protobuf.CodedInputStream input,
        org.jetbrains.kotlin.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall> annotation_ =
      java.util.Collections.emptyList();
    private void ensureAnnotationIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        annotation_ = new java.util.ArrayList<org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall>(annotation_);
        bitField0_ |= 0x00000001;
       }
    }

    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public java.util.List<org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall> getAnnotationList() {
      return java.util.Collections.unmodifiableList(annotation_);
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public int getAnnotationCount() {
      return annotation_.size();
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall getAnnotation(int index) {
      return annotation_.get(index);
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder setAnnotation(
        int index, org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureAnnotationIsMutable();
      annotation_.set(index, value);

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder setAnnotation(
        int index, org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall.Builder builderForValue) {
      ensureAnnotationIsMutable();
      annotation_.set(index, builderForValue.build());

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder addAnnotation(org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureAnnotationIsMutable();
      annotation_.add(value);

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder addAnnotation(
        int index, org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureAnnotationIsMutable();
      annotation_.add(index, value);

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder addAnnotation(
        org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall.Builder builderForValue) {
      ensureAnnotationIsMutable();
      annotation_.add(builderForValue.build());

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder addAnnotation(
        int index, org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall.Builder builderForValue) {
      ensureAnnotationIsMutable();
      annotation_.add(index, builderForValue.build());

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder addAllAnnotation(
        java.lang.Iterable<? extends org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall> values) {
      ensureAnnotationIsMutable();
      org.jetbrains.kotlin.protobuf.AbstractMessageLite.Builder.addAll(
          values, annotation_);

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder clearAnnotation() {
      annotation_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);

      return this;
    }
    /**
     * <code>repeated .org.jetbrains.kotlin.backend.common.serialization.proto.IrConstructorCall annotation = 1;</code>
     */
    public Builder removeAnnotation(int index) {
      ensureAnnotationIsMutable();
      annotation_.remove(index);

      return this;
    }

    private long classifier_ ;
    /**
     * <code>required int64 classifier = 2;</code>
     */
    public boolean hasClassifier() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int64 classifier = 2;</code>
     */
    public long getClassifier() {
      return classifier_;
    }
    /**
     * <code>required int64 classifier = 2;</code>
     */
    public Builder setClassifier(long value) {
      bitField0_ |= 0x00000002;
      classifier_ = value;
      
      return this;
    }
    /**
     * <code>required int64 classifier = 2;</code>
     */
    public Builder clearClassifier() {
      bitField0_ = (bitField0_ & ~0x00000002);
      classifier_ = 0L;
      
      return this;
    }

    private boolean hasQuestionMark_ ;
    /**
     * <code>required bool has_question_mark = 3;</code>
     */
    public boolean hasHasQuestionMark() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required bool has_question_mark = 3;</code>
     */
    public boolean getHasQuestionMark() {
      return hasQuestionMark_;
    }
    /**
     * <code>required bool has_question_mark = 3;</code>
     */
    public Builder setHasQuestionMark(boolean value) {
      bitField0_ |= 0x00000004;
      hasQuestionMark_ = value;
      
      return this;
    }
    /**
     * <code>required bool has_question_mark = 3;</code>
     */
    public Builder clearHasQuestionMark() {
      bitField0_ = (bitField0_ & ~0x00000004);
      hasQuestionMark_ = false;
      
      return this;
    }

    private java.util.List<java.lang.Long> argument_ = java.util.Collections.emptyList();
    private void ensureArgumentIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        argument_ = new java.util.ArrayList<java.lang.Long>(argument_);
        bitField0_ |= 0x00000008;
       }
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public java.util.List<java.lang.Long>
        getArgumentList() {
      return java.util.Collections.unmodifiableList(argument_);
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public int getArgumentCount() {
      return argument_.size();
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public long getArgument(int index) {
      return argument_.get(index);
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public Builder setArgument(
        int index, long value) {
      ensureArgumentIsMutable();
      argument_.set(index, value);
      
      return this;
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public Builder addArgument(long value) {
      ensureArgumentIsMutable();
      argument_.add(value);
      
      return this;
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public Builder addAllArgument(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureArgumentIsMutable();
      org.jetbrains.kotlin.protobuf.AbstractMessageLite.Builder.addAll(
          values, argument_);
      
      return this;
    }
    /**
     * <code>repeated int64 argument = 4 [packed = true];</code>
     *
     * <pre>
     * 0 - STAR, otherwise [63..2 - IrType index | 1..0 - Variance]
     * </pre>
     */
    public Builder clearArgument() {
      argument_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      
      return this;
    }

    // @@protoc_insertion_point(builder_scope:org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy)
  }

  static {
    defaultInstance = new IrSimpleTypeLegacy(true);
    defaultInstance.initFields();
  }

  // @@protoc_insertion_point(class_scope:org.jetbrains.kotlin.backend.common.serialization.proto.IrSimpleTypeLegacy)
}
